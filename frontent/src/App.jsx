import './App.css';
import { Routes, Route, Link, useNavigate } from 'react-router-dom';
import Players from './pages/Players';
import Clubs from './pages/Clubs';
import Transfers from './pages/Transfers';
import Competitions from './pages/Competitions';
import LoginPage from './pages/Login';
import ProtectedRoute from './components/ProtectedRoute';
import { logout } from './services/api';

function App() {
  const navigate = useNavigate();
  const token = localStorage.getItem('token');

  const handleLogout = () => {
    logout();
    navigate('/login');
  };

  return (
    <>
      <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
        <div className="container-fluid">
          <Link className="navbar-brand" to="/">Football Stats</Link>
          <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span className="navbar-toggler-icon"></span>
          </button>
          <div className="collapse navbar-collapse" id="navbarNav">
            <ul className="navbar-nav me-auto mb-2 mb-lg-0">
              <li className="nav-item">
                <Link className="nav-link" to="/players">Players</Link>
              </li>
              <li className="nav-item">
                <Link className="nav-link" to="/clubs">Clubs</Link>
              </li>
              <li className="nav-item">
                <Link className="nav-link" to="/transfers">Transfers</Link>
              </li>
              <li className="nav-item">
                <Link className="nav-link" to="/competitions">Competitions</Link>
              </li>
            </ul>
            {token && (
              <button onClick={handleLogout} className="btn btn-danger">
                Logout
              </button>
            )}
          </div>
        </div>
      </nav>
      <div className="container mt-4">
        <Routes>
          <Route path="/login" element={<LoginPage />} />
          <Route element={<ProtectedRoute />}>
            <Route path="/" element={<Players />} />
            <Route path="/players" element={<Players />} />
            <Route path="/clubs" element={<Clubs />} />
            <Route path="/transfers" element={<Transfers />} />
            <Route path="/competitions" element={<Competitions />} />
          </Route>
        </Routes>
      </div>
    </>
  );
}

export default App;

