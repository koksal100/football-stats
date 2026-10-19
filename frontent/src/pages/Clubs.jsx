import React, { useState, useEffect, useCallback } from 'react';
import { getMostScorerClubs } from '../services/api';

const Clubs = () => {
  const [clubs, setClubs] = useState([]);
  const [year, setYear] = useState(2023);
  const [size, setSize] = useState(10);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  const fetchClubs = useCallback(() => {
    setLoading(true);
    setError(null);
    getMostScorerClubs(year, size)
      .then(response => {
        setClubs(response.data);
        setLoading(false);
      })
      .catch(error => {
        setError('Error fetching data');
        setLoading(false);
        console.error('There was an error fetching the clubs!', error);
      });
  }, [year, size]);

  useEffect(() => {
    fetchClubs();
  }, [fetchClubs]);

  return (
    <div className="container my-4">
      <h1 className="text-center mb-4">Most Scorer Clubs</h1>
      <div className="row g-3 align-items-end mb-4">
        <div className="col-md-3">
          <label htmlFor="yearInput" className="form-label">
            Year:
          </label>
          <input type="number" id="yearInput" className="form-control" value={year} onChange={e => setYear(e.target.value)} />
        </div>
        <div className="col-md-3">
          <label htmlFor="sizeInput" className="form-label">
            Count:
          </label>
          <input type="number" id="sizeInput" className="form-control" value={size} onChange={e => setSize(e.target.value)} />
        </div>
      </div>

      {loading && <p className="text-center">Loading...</p>}
      {error && <p className="text-center text-danger">{error}</p>}

      <div className="table-responsive">
        <table className="table table-striped table-hover">
          <thead>
            <tr>
              <th>Name</th>
              <th>Goals</th>
            </tr>
          </thead>
          <tbody>
            {clubs.map(club => (
              <tr key={club.id}>
                <td>{club.name}</td>
                <td>{club["Requested Informations"] ? club["Requested Informations"].total_goals : 'N/A'}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default Clubs;
