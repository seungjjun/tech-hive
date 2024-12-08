const API_BASE = process.env.REACT_APP_API_BASE;

export const fetchSearchTechArticles = async (searchTerm, page, limit) => {
    const response = await fetch(`${API_BASE}/tech-articles/search?term=${searchTerm}&page=${page}&limit=${limit}`);
    if (!response.ok) {
        throw new Error('Failed to fetch search');
    }
    return await response.json();
};
