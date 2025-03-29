import React from 'react';
import { Routes, Route } from 'react-router-dom';
import Navbar from './Components/NavBar/Navbar';
import Home from './Pages/Home';
import './App.css';

const App = () => {
  console.log("App rendering");
  
  return (
    <div className="app-container">
      <Navbar />
      <div className="main-content" style={{ marginTop: '80px', padding: '20px' }}>
        <Routes>
          <Route path="/" element={<Home />} />
        </Routes>
      </div>
    </div>
  );
};

export default App;