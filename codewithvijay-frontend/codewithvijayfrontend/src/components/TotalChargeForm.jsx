import React, { useState, useEffect } from 'react';
import axios from 'axios';

const TotalChargeForm = () => {
  const [formData, setFormData] = useState({
    name: '',
    email: '',
    mobileNumber: '',
    from: 'Bangalore',
    to: '',
    weight: ''
  });
  const [toCities, setToCities] = useState([]);
  const [submitSuccess, setSubmitSuccess] = useState(false);

  useEffect(() => {
    // Fetch to cities from backend when component mounts
    fetchToCities();
  }, []);

  const fetchToCities = async () => {
    try {
        const response = await axios.get('http://localhost:8080/api/getCities');
        setToCities(response.data);
    } catch (error) {
        console.error('Error fetching to cities:', error);
    }
};

  const handleChange = e => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async e => {
    e.preventDefault();
    try {
      const response = await axios.post('http://localhost:8080/totalcharge', formData);
      console.log('Form submitted successfully:', response.data);
      setSubmitSuccess(true);
    } catch (error) {
      console.error('Error submitting form:', error);
    }
  };

  return (
    <div>
      <h1>Total Charge Form</h1>
      {submitSuccess && <p>Form submitted successfully!</p>}
      <form onSubmit={handleSubmit}>
        <label>
          Name:
          <input
            type="text"
            name="name"
            value={formData.name}
            onChange={handleChange}
            required
          />
        </label>
        <br />
        <label>
          Email:
          <input
            type="email"
            name="email"
            value={formData.email}
            onChange={handleChange}
            required
          />
        </label>
        <br />
        <label>
          Mobile Number:
          <input
            type="text"
            name="mobileNumber"
            value={formData.mobileNumber}
            onChange={handleChange}
            required
          />
        </label>
        <br />
        <label>
          From:
          <input
            type="text"
            name="from"
            value={formData.from}
            onChange={handleChange}
            disabled
          />
        </label>
        <br />
        <label>
          To:
          <select name="to" value={formData.to} onChange={handleChange} required>
            <option value="">Select a city</option>
            {toCities.map(city => (
              <option key={city} value={city}>
                {city}
              </option>
            ))}
          </select>
        </label>
        <br />
        <label>
          Weight:
          <input
            type="number"
            name="weight"
            value={formData.weight}
            onChange={handleChange}
            required
          />
        </label>
        <br />
        <button type="submit">Submit</button>
      </form>
    </div>
  );
};

export default TotalChargeForm;
