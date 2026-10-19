import React, { useState, useEffect, useCallback } from 'react';
import { getGamesByCompetition } from '../services/api';

const Competitions = () => {
  const [games, setGames] = useState([]);
  const [competitionId, setCompetitionId] = useState('GB1');
  const [season, setSeason] = useState(2023);
  const [page, setPage] = useState(0);
  const [size, setSize] = useState(10);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  const fetchGames = useCallback(() => {
    setLoading(true);
    setError(null);
    getGamesByCompetition(competitionId, season, page, size)
      .then(response => {
        setGames(response.data.content);
        setLoading(false);
      })
      .catch(error => {
        setError('Error fetching data');
        setLoading(false);
        console.error('There was an error fetching the games!', error);
      });
  }, [competitionId, season, page, size]);

  useEffect(() => {
    fetchGames();
  }, [fetchGames]);

  return (
    <div className="container my-4">
      <h1 className="text-center mb-4">Games by Competition</h1>
      <div className="row g-3 align-items-end mb-4">
        <div className="col-md-3">
          <label htmlFor="competitionIdInput" className="form-label">
            Competition ID:
          </label>
          <input type="text" id="competitionIdInput" className="form-control" value={competitionId} onChange={e => setCompetitionId(e.target.value)} />
        </div>
        <div className="col-md-3">
          <label htmlFor="seasonInput" className="form-label">
            Season:
          </label>
          <input type="number" id="seasonInput" className="form-control" value={season} onChange={e => setSeason(e.target.value)} />
        </div>
        <div className="col-md-3">
          <label htmlFor="pageInput" className="form-label">
            Page:
          </label>
          <input type="number" id="pageInput" className="form-control" value={page} onChange={e => setPage(e.target.value)} />
        </div>
        <div className="col-md-3">
          <label htmlFor="sizeInput" className="form-label">
            Size:
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
              <th>Home Club</th>
              <th>Away Club</th>
              <th>Home Goals</th>
              <th>Away Goals</th>
              <th>Date</th>
            </tr>
          </thead>
          <tbody>
            {games.map(game => (
              <tr key={game.id}>
                <td>{game.homeClub ? game.homeClub.name : 'N/A'}</td>
                <td>{game.awayClub ? game.awayClub.name : 'N/A'}</td>
                <td>{game.homeClubGoals}</td>
                <td>{game.awayClubGoals}</td>
                <td>{new Date(game.date).toLocaleDateString()}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default Competitions;
