import React, { useState } from 'react';
import '../styles.css'

const Navbar = () => {
  const [isMenuOpen, setIsMenuOpen] = useState(false);

  // Function to toggle the menu
  const handleMenuToggle = () => {
    setIsMenuOpen((prevState) => !prevState);
  };

  // Function to close the menu
  const closeMenu = () => {
    setIsMenuOpen(false);
  };

  return (
    <nav className="navbar">
      <div className="navbar__container">
        <a href="/" id="navbar__logo">DREAM DECORATIONS</a>
        <div
          className={`navbar__toggle ${isMenuOpen ? 'is-active' : ''}`}
          id="mobile-menu"
          onClick={handleMenuToggle}
        >
          <span className="bar"></span>
          <span className="bar"></span>
          <span className="bar"></span>
        </div>
        <ul className={`navbar__menu ${isMenuOpen ? 'active' : ''}`}>
          <li className="navbar__item">
            <a href="#home" className="navbar__links" onClick={closeMenu}>
              Hauptseite
            </a>
          </li>
          <li className="navbar__item">
            <a href="#about" className="navbar__links" onClick={closeMenu}>
              Über uns
            </a>
          </li>
          <li className="navbar__item">
            <a
              href="https://dreamdecorations.pixieset.com/galerie/"
              target="_blank"
              className="navbar__links"
              rel="noopener noreferrer"
              onClick={closeMenu}
            >
              Galerie
            </a>
          </li>
          <li className="navbar__item">
            <a href="#services" className="navbar__links" onClick={closeMenu}>
              Dienstleistungen
            </a>
          </li>
          <li className="navbar__btn">
            <a href="#contact" className="button" onClick={closeMenu}>
              Kontaktiere uns
            </a>
          </li>
        </ul>
      </div>
    </nav>
  );
};

export default Navbar;
