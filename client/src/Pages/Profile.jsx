import React, { useState, useEffect } from 'react';
import './profile.css';

const LoginSignup = () => {
  return (
    <div className="container">
      <div className="header">
        <div className="text">Sign Up</div>
        <div className="underline"></div>
      </div>

      <div className="inputs">
        <div className="input">
        <img src="profile.png" alt="profile" className="profile-icon" />
          <input type="text" />
        </div>

        <div className="input">
          <input type="email" />
        </div>

        <div className="input">
          <input type="password" />
        </div>
      </div>
    </div>
  );
};

export default LoginSignup;
