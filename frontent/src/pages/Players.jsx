import React, { useState, useEffect, useCallback } from 'react';
import { getTopScorers, getMostRisingPlayers, getMostRedCardedPlayers, getTopAssisters } from '../services/api';

const playerCategories = {
  scorers: { fn: getTopScorers, title: 'Top Scorers', stat: 'goals' },
  rising: { fn: getMostRisingPlayers, title: 'Most Rising Players', stat: 'value_increase_in_eur' },
  reds: { fn: getMostRedCardedPlayers, title: 'Most Red Carded Players', stat: 'red_cards' },
  assisters: { fn: getTopAssisters, title: 'Top Assisters', stat: 'assists' },
};

const Players = () => {
  const [players, setPlayers] = useState([]);
  const [year, setYear] = useState(2023);
  const [size, setSize] = useState(10);
  const [category, setCategory] = useState('scorers');
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  const fetchPlayers = useCallback(() => {
    setLoading(true);
    setError(null);
    const { fn } = playerCategories[category];
    fn(year, size)
      .then(response => {
        setPlayers(response.data);
        setLoading(false);
      })
      .catch(error => {
        setError('Error fetching data');
        setLoading(false);
        console.error(`There was an error fetching the ${category}!`, error);
      });
  }, [year, size, category]);

  useEffect(() => {
    fetchPlayers();
  }, [fetchPlayers]);

  const { title, stat } = playerCategories[category];

  return (
    <div className="container my-4">
      <h1 className="text-center mb-4">{title}</h1>
      <div className="row g-3 align-items-end mb-4">
        <div className="col-md-3">
          <label htmlFor="categorySelect" className="form-label">
            Category:
          </label>
          <select id="categorySelect" className="form-select" value={category} onChange={e => setCategory(e.target.value)}>
            {Object.keys(playerCategories).map(key => (
              <option key={key} value={key}>{playerCategories[key].title}</option>
            ))}
          </select>
        </div>
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
              <th>{stat.charAt(0).toUpperCase() + stat.slice(1)}</th>
            </tr>
          </thead>
          <tbody>
            {players.map(player => (
              <tr key={player.id}>
                <td>{player.name}</td>
                <td>{player["Requested Informations"] ? player["Requested Informations"][stat] : 'N/A'}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default Players;
