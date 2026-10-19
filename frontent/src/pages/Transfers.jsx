import React, { useState, useEffect, useCallback } from 'react';
import { getMostValuableTransfers } from '../services/api';

const Transfers = () => {
  const [transfers, setTransfers] = useState([]);
  const [year, setYear] = useState(2023);
  const [size, setSize] = useState(10);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  const fetchTransfers = useCallback(() => {
    setLoading(true);
    setError(null);
    getMostValuableTransfers(year, size)
      .then(response => {
        setTransfers(response.data);
        setLoading(false);
      })
      .catch(error => {
        setError('Error fetching data');
        setLoading(false);
        console.error('There was an error fetching the transfers!', error);
      });
  }, [year, size]);

  useEffect(() => {
    fetchTransfers();
  }, [fetchTransfers]);

  return (
    <div className="container my-4">
      <h1 className="text-center mb-4">Most Valuable Transfers</h1>
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
              <th>Player Name</th>
              <th>Fee</th>
              <th>From Club</th>
              <th>To Club</th>
            </tr>
          </thead>
          <tbody>
            {transfers.map(transfer => (
              <tr key={transfer.id}>
                <td>{transfer.player ? transfer.player.name : 'N/A'}</td>
                <td>{transfer.transferFee ? transfer.transferFee : 'N/A'}</td>
                <td>{transfer.fromClub ? transfer.fromClub.name : 'N/A'}</td>
                <td>{transfer.toClub ? transfer.toClub.name : 'N/A'}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default Transfers;
