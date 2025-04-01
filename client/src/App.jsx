import React from 'react';
import Navbar from './Components/NavBar/Navbar';
import { Routes, Route } from 'react-router-dom';
import Home from './Pages/Home';
import PopularScrolls from './Pages/PopularScrolls';
import Fantasy from './Pages/Fantasy';
import Mystery from './Pages/Mystery';
import Horror from './Pages/Horror';
import ScienceFiction from './Pages/ScienceFiction';
import Romance from './Pages/Romance';
import Fiction from './Pages/Fiction';
import Drama from './Pages/Drama';
import Cart from './Pages/Cart';
import SearchResults from './Pages/SearchResult';
import './App.css';

const App = () => {
  console.log("App rendering");
  
  
  return (
    <>
      <Navbar />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/popular" element={<PopularScrolls />} />
        <Route path="/fantasy" element={<Fantasy />} />
        <Route path="/mystery" element={<Mystery />} />
        <Route path="/horror" element={<Horror />} />
        <Route path="/scifi" element={<ScienceFiction />} />
        <Route path="/romance" element={<Romance />} />
        <Route path="/search" element={<SearchResults />} />
        <Route path="/fiction" element={<Fiction />} />
        <Route path="/drama" element={<Drama />} />
        <Route path="/cart" element={<Cart />} />
      </Routes>
    </>
  );
}

export default App;