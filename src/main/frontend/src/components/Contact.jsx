import React from 'react';
import '../styles.css';

function Contact() {
  return (
    <div className="contact" id="contact">
      <div className="contact__container">
        <div className="form">
          <div className="contact-info">
            <h3 className="title">Lass uns Kontakt aufnehmen!</h3>
            <p className="text">Wir freuen uns darauf, uns kennenzulernen...</p>
          </div>
          <div className="contact-form">
            <form action="https://submit-form.com/sVqEj3FvV" method="POST">
              <div className="input-container">
                <input type="text" name="name" className="input" required />
                <label>Name</label>
              </div>
              <div className="input-container">
                <input type="email" name="email" className="input" required />
                <label>Email</label>
              </div>
              <div className="input-container">
                <input type="tel" name="phone" className="input" required />
                <label>Telefonnummer</label>
              </div>
              <div className="input-container textarea">
                <textarea name="message" className="input" required></textarea>
                <label>Nachricht</label>
              </div>
              <input type="submit" value="Schicken" className="btn" />
            </form>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Contact;