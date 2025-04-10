import React, { useState, useEffect } from 'react';
import './Profile.css';

const Profile = () => {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [isSigningUp, setIsSigningUp] = useState(false);
  const [formData, setFormData] = useState({
    username: '',
    email: '',
    password: '',
    address: ''
  });

  // Mock login check (replace with real auth check later)
  useEffect(() => {
    const user = localStorage.getItem('user');
    if (user) {
      setIsLoggedIn(true);
    }
  }, []);

  const handleChange = (e) => {
    setFormData((prev) => ({
      ...prev,
      [e.target.name]: e.target.value,
    }));
  };

  const handleLogin = (e) => {
    e.preventDefault();
    // Call your backend login API here
    // Example: await loginUser(formData.username, formData.password)
    localStorage.setItem('user', JSON.stringify({ username: formData.username }));
    setIsLoggedIn(true);
  };

  const handleSignup = (e) => {
    e.preventDefault();
    // Call your backend signup API here
    // Example: await createUser(formData)
    localStorage.setItem('user', JSON.stringify({ username: formData.username }));
    setIsLoggedIn(true);
  };

  const handleLogout = () => {
    localStorage.removeItem('user');
    setIsLoggedIn(false);
    setFormData({ username: '', email: '', password: '', address: '' });
  };

  if (!isLoggedIn) {
    return (
      <div className="profile-page-wrapper">
        <div className="profile-container">
          <h2>{isSigningUp ? 'Sign Up' : 'Login'}</h2>
          <form onSubmit={isSigningUp ? handleSignup : handleLogin}>
            <input
              type="text"
              name="username"
              placeholder="Username"
              value={formData.username}
              onChange={handleChange}
              required
            />
            {isSigningUp && (
              <>
                <input
                  type="email"
                  name="email"
                  placeholder="Email"
                  value={formData.email}
                  onChange={handleChange}
                  required
                />
                <input
                  type="text"
                  name="address"
                  placeholder="Address"
                  value={formData.address}
                  onChange={handleChange}
                />
              </>
            )}
            <input
              type="password"
              name="password"
              placeholder="Password"
              value={formData.password}
              onChange={handleChange}
              required
            />
            <button type="submit">{isSigningUp ? 'Create Account' : 'Login'}</button>
          </form>
          <p>
            {isSigningUp ? 'Already have an account?' : "Don't have an account?"}{' '}
            <button onClick={() => setIsSigningUp(!isSigningUp)}>
              {isSigningUp ? 'Login here' : 'Sign up here'}
            </button>
          </p>
        </div>
      </div>
    );
  }
  

 // User is logged in
return (
  <div className="profile-page-wrapper">
    <div className="profile-container">
      <h2>Welcome, {formData.username}!</h2>
      <p>What would you like to do?</p>

      <div className="profile-links">
        <button onClick={() => window.location.href = '/cart'}>
          🛒 Go to Shopping Cart
        </button>

        <button onClick={() => window.location.href = '/wishlist'}>
          ❤️ Go to Wishlist
        </button>

        <button onClick={() => window.location.href = '/'}>
          🏠 Return to Home Page
        </button>

        <button onClick={() => window.location.href = '/my-comments'}>
          📚 View Books You’ve Commented On
        </button>
      </div>

      <button className="logout-btn" onClick={handleLogout}>Logout</button>
    </div>
  </div>
);
}

export default Profile;
