import React from 'react';

function Hero() {
  return (
    <div className="main" id="home">
      <div className="main__container">
        <div className="main__content">
          <h1>DREAM DECORATIONS</h1>
          <h2>IHRE VERANSTALTUNG</h2>
          <p>Sehen Sie, was uns von anderen unterscheidet</p>
          <button className="main__btn">
            <a href="#gallery">GALERIE</a>
          </button>
        </div>
      </div>
    </div>
  );
}

export default Hero;
