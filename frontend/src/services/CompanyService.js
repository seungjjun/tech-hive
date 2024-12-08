const API_BASE = process.env.REACT_APP_API_BASE;

export const fetchCompanies = async (sort = 'RECENT', page = '1', size = 10) => {
    const response = await fetch(`${API_BASE}/companies?sort=${sort}&page=${page}&size=${size}`);
    if (!response.ok) {
        throw new Error('Failed to fetch companies');
    }
    const data = await response.json();
    return data.companies;
};

export const fetchCompany = async (id) => {
    const response = await fetch(`${API_BASE}/companies/${id}`);
    if (!response.ok) {
        throw new Error('Failed to fetch company');
    }
    return await response.json();
}
