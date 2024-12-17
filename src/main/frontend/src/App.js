import React from 'react';
import Navbar from './components/Navbar';
import Hero from './components/Hero';
import About from './components/About';
import Gallery from './components/Gallery';
import Services from './components/Services';
import Contact from './components/Contact';
import Footer from './components/Footer';
import './styles.css';

function App() {
  return (
    <>
      <Navbar />
      <Hero />
      <About />
      <Gallery />
      <Services />
      <Contact />
      <Footer />
    </>
  );
}

export default App;
