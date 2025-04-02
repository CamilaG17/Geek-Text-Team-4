import React, { useState, useEffect } from 'react'
import './Navbar.css' 
import { Link, useLocation, useNavigate } from 'react-router-dom';

const Navbar = () => {
  const location = useLocation();
  const navigate = useNavigate();
  const [activePage, setActivePage] = useState('/');
  const [searchQuery, setSearchQuery]= useState('');

  // Update active page when location changes
  useEffect(() => {
    setActivePage(location.pathname);
  }, [location]);

  const handleSearchChange = (e) => {
    setSearchQuery(e.target.value);
  };
  // Handle search submission
  const handleSearchSubmit = (e) => {
    e.preventDefault();
    if (searchQuery.trim()) {
      navigate(`/search?q=${encodeURIComponent(searchQuery.trim())}`);
    }
  };

  
  return (
   
  <nav className='flex-div'>
      <div className='nav-left flex-div'>
        <Link to="/">
          <img className='Logo-icon' src="logo.png" alt="Logo Icon" /> 
        </Link>
      </div>
      <div className="nav-middle flex-div">
        <form className="search-box flex-div" onSubmit={handleSearchSubmit}>
          <input 
            className='Search' 
            type="text" 
            placeholder='Search by Title, Author or ISBN'  
            value={searchQuery}
            onChange={handleSearchChange}
            style={{ WebkitTextFillColor: '#3E2723', textAlign: 'left', display: 'flex', alignItems: 'center', height: '30px'}} 
          /> 
          <button type="submit" style={{ background: 'transparent', border: 'none', cursor: 'pointer' }}>
            <img src="search.png" alt="Search Icon" />
          </button>
        </form>
      </div>


    <div className="nav-right">

      <div className="wishlist-container">
          <div  onClick={() => window.location.href='/wishlist'}> {/* Redirect to wishlist page on click */} 
          <img className="wishlist" src="wishlist.png" alt="Wishlist Icon" />
          <span className="wishlist-text">Wishlist</span>
          </div>
      </div>

      <div className="profile-container">
          <div onClick={() => window.location.href='/profile'}> {/* Redirect to profile page on click */}
          <img className="profile" src="profile.png" alt="Profile Icon" />
          <span className="profile-text">Profile</span>
          </div>
      </div>

      <div className ="cart-container">
        <div onClick={() => window.location.href='/cart'}> {/* Redirect to cart page on click */} 
        <img className ="cart" src="Cart.png" alt="Cart Icon"/>
        <span className ="cart-text">Cart</span>
        </div>
      </div>
    </div>
    <div className="nav-bottom">
        <div className='nav-bottom-left'>
          <Link to="/popular">
            <button className={activePage === '/popular' ? 'active-button' : ''}>
              <span className='nav-bottom-popular'>Popular Scrolls</span>
            </button>
          </Link>
          <Link to="/fantasy">
            <button className={activePage === '/fantasy' ? 'active-button' : ''}>
              <span className='nav-bottom-fantasy'>Fantasy</span>
            </button>
          </Link>
          <Link to="/mystery">
            <button className={activePage === '/mystery' ? 'active-button' : ''}>
              <span className='nav-bottom-hystery'>Mystery</span>
            </button>
          </Link>
          <Link to="/Drama">
            <button className={activePage === '/Drama' ? 'active-button' : ''}>
              <span className='nav-bottom-hystery'>Drama</span>
            </button>
          </Link>
          <Link to="/Fiction">
            <button className={activePage === '/Fiction' ? 'active-button' : ''}>
              <span className='nav-bottom-hystery'>Fiction</span>
            </button>
          </Link>
          <Link to="/horror">
            <button className={activePage === '/horror' ? 'active-button' : ''}>
              <span className='nav-bottom-horror'>Horror</span>
            </button>
          </Link>
          <Link to="/scifi">
            <button className={activePage === '/scifi' ? 'active-button' : ''}>
              <span className='nav-bottom-scifi'>Science Fiction</span>
            </button>
          </Link>
          <Link to="/romance">
            <button className={activePage === '/romance' ? 'active-button' : ''}>
              <span className='nav-bottom-rom'>Romance</span>
            </button>
          </Link>
        </div>
      </div>
    </nav>
  );
};

export default Navbar; // Exporting the Navbar component for use in other parts of the app