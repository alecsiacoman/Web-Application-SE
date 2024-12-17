import React from 'react';
import '../styles.css';

function Footer() {
  return (
    <div className="footer__container">
      <div className="footer__links">
        <div className="footer__link--wrapper">
          <div className="footer__logo">
            {/* <a href="#home"><img src={logo} alt="Logo" /></a> */}
          </div>
          <div className="footer__link--items">
            <h2>Dienstleistungen</h2>
            <a href="#">Hochzeit</a>
            <a href="#">Geburtstag</a>
            <a href="#">Taufe</a>
            <a href="#">Jede Art von Veranstaltung</a>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Footer;