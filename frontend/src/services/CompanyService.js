const API_BASE = 'http://localhost:8000/api';

export const fetchCompanies = async (sort = 'RECENT_ARTICLE', page = '1', size = 10) => {
    const response = await fetch(`${API_BASE}/companies?sort=${sort}&page=${page}&size=${size}`);
    if (!response.ok) {
        throw new Error('Failed to fetch companies');
    }
    const data = await response.json();
    return data.companies;
};
