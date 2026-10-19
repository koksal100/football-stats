import React, { useState } from 'react';
import '../styles/InfoButton.css';

const InfoButton = ({ description }) => {
  const [isOpen, setIsOpen] = useState(false);

  const toggleOpen = () => {
    setIsOpen(!isOpen);
  };

  return (
    <div className="info-button-container">
      <button onClick={toggleOpen} className="info-button">
        {isOpen ? 'Hide Info' : 'Show Info'}
      </button>
      {isOpen && (
        <div className="info-box">
          <p>{description}</p>
        </div>
      )}
    </div>
  );
};

export default InfoButton;