import React from 'react'
import './Navbar.css' // Importing the CSS file for the Navbar component
import logo_icon from '/logo.png'; // Importing the logo icon
import search_icon from '/search.png'; // Importing the search icon
import wishlist_icon from '/wishlist.png'; // Importing the notification icon
import profile_icon from '/profile.png'; // Importing the user profile icon
import cart_icon from '/Cart.png'; // Importing the cart icon

// Navbar component definition
const Navbar = () => {
  return (
   
    <nav className='flex-div'>
      
      <div className='nav-left flex-div'>
      {
        /* I moved the logos to public folder cause thats convention.
        Also the convention is to inline like src="/logo.png" instead of importing it like we did before. */ 
      }
        <img className='Logo-icon' src={logo_icon} alt="Logo Icon" /> 
      </div>
  
      <div className="nav-middle flex-div">
        <div className="search-box flex-div">
          <input className = 'Search' type="text" placeholder='Search by Title, Author or ISBN'  style={{ WebkitTextFillColor: '#3E2723', textAlign: 'left', display: 'flex',alignItems: 'center', height: '30px'}}  /> 
          <img src={search_icon} alt="Search Icon" /> 
        </div>
      </div> 


      <div className="nav-right">
    <div className="wishlist-container">
        <img className="wishlist" src={wishlist_icon} alt="Wishlist Icon" />
        <span className="wishlist-text">Wishlist</span>
    </div>
    <div className="profile-container">
        <img className="profile" src={profile_icon} alt="Profile Icon" />
        <span className="profile-text">Profile</span>
    </div>
    <div className ="cart-container">
      <img className ="cart" src={cart_icon} alt="Cart Icon"/>
      <span className ="cart-text">Cart</span>
    </div>
</div>
    <div className="nav-bottom">
        <div className='nav-bottom-left'>
    <button>
        <span className='nav-bottom-popular'>Popular Scrolls</span>
    </button>
    <button>
        <span className='nav-bottom-fantasy'>Fantasy</span>
    </button>
    <button>
        <span className='nav-bottom-hystery'>Mystery</span>
    </button>
    <button>
        <span className='nav-bottom-horror'>Horror</span>
    </button>
    <button>
        <span className='nav-bottom-scifi'>Science Fiction</span>
    </button>
    <button>
        <span className='nav-bottom-rom'>Romance</span>
    </button>
      </div>
      </div>

      
    </nav>


  );
};

export default Navbar; // Exporting the Navbar component for use in other parts of the app