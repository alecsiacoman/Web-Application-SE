import React from 'react';
import '../styles.css';

function Services() {
  return (
    <div className="services" id="services">
      <h1>Wir lassen Ihre Traumveranstaltung wahr werden</h1>
      <div className="services__container">
        {['Taufe', 'Hochzeit', 'Geburtstag'].map((service, index) => (
          <div className="services__card" key={index}>
            <h2>{service}</h2>
            <p>Eine unvergessliche Erinnerung</p>
            <button><a href="#contact">Kontaktiere uns</a></button>
          </div>
        ))}
      </div>
    </div>
  );
}

export default Services;