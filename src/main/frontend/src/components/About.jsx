// About.jsx
import React from 'react';
import '../styles.css';
import aboutUsImg from '../assets/images/aboutUs.jpg';

function About() {
  return (
    <div className="about" id="about">
      <h1 className="heading"><span>Über</span> uns</h1>
      <div className="row">
        <div className="image">
          <img src={aboutUsImg} alt="About Us" />
        </div>
        <div className="about__content">
          <h2 style={{ color: 'white' }}>Wer sind wir?</h2>
          <p>Wir sind ein junges und leidenschaftliches Team, das sich darauf spezialisiert hat, jedes Event in ein unvergessliches Erlebnis zu verwandeln...</p>
        </div>
      </div>
    </div>
  );
}

export default About;