import React, { useState } from 'react';
import logo from './logo.svg';
import axios from 'axios';
import './App.css';



function App() {

  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  const handleSubmit = (event) => {
    
    event.preventDefault();

    const data = {
      username: email,
      password: password,
    };

    axios.post('http://localhost:8080/sign-in', data)
      .then((response) => {
        console.log(response.data);
      })
      .catch((error) => {
        console.error('Error:', error);
      });
  };

  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />       
        <form onSubmit={handleSubmit}>
          <div class="mb-3">
            <label htmlFor="exampleInputEmail1" className="form-label">Email address</label>
            <input type="email" className="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" value={email} onChange={(e) => setEmail(e.target.value)} />
          </div>
          <div class="mb-3">
            <label htmlFor="exampleInputPassword1" className="form-label">Password</label>
            <input type="password" className="form-control" id="exampleInputPassword1" value={password} onChange={(e) => setPassword(e.target.value)} />
          </div>
          <button type="submit" class="btn btn-dark">Submit</button>
        </form>
      </header>
    </div>
  );
}

export default App;
