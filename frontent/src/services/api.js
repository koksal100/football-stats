import axios from 'axios';

const api = axios.create({
  // VITE_API_URL ile override edilebilir; varsayılan local geliştirme adresi.
  baseURL: import.meta.env.VITE_API_URL || 'http://localhost:8080',
});

// Request interceptor to add the auth token header to requests
api.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem('token');
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

// --- AUTH --- //
export const login = (credentials) => api.post('/authenticate', credentials);

export const logout = () => {
  localStorage.removeItem('token');
  // No backend call needed for this simple JWT implementation
};

// --- PLAYERS --- //
export const getTopScorers = (year, size) => api.get(`/player/scorers?year=${year}&size=${size}`);
export const getMostRisingPlayers = (year, size) => api.get(`/player/rising?year=${year}&size=${size}`);
export const getMostRedCardedPlayers = (year, size) => api.get(`/player/reds?year=${year}&size=${size}`);
export const getTopAssisters = (year, size) => api.get(`/player/assisters?year=${year}&size=${size}`);

// --- CLUBS --- //
export const getMostScorerClubs = (year, size) => api.get(`/club/most-scorer-clubs-by-year?year=${year}&size=${size}`);

// --- TRANSFERS --- //
export const getMostValuableTransfers = (year, size) => api.get(`/transfer/most-valuable?year=${year}&size=${size}`);

// --- COMPETITIONS --- //
export const getGamesByCompetition = (competitionId, season, page, size) => api.get(`/competition/${competitionId}/games?season=${season}&page=${page}&size=${size}`);
