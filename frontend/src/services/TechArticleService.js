const API_BASE = 'http://localhost:8000/api';

export const fetchTechArticles = async (page, size, sort = 'RECENT') => {
    const response = await fetch(`${API_BASE}/tech-articles?page=${page}&size=${size}&sort=${sort}`);
    if (!response.ok) {
        throw new Error('Failed to fetch tech articles');
    }
    const data = await response.json();
    return data.techArticleResponseList;
};

export const fetchPopularTechArticles = async () => {
    const response = await fetch(`${API_BASE}/tech-articles/popular`);
    if (!response.ok) {
        throw new Error('Failed to fetch popular tech articles');
    }
    const data = await response.json();
    return data.techArticleResponseList;
};

export const fetchRecommendTechArticles = async () => {
    const response = await fetch(`${API_BASE}/tech-articles/recommend`);
    if (!response.ok) {
        throw new Error('Failed to fetch recommend tech articles');
    }
    const data = await response.json();
    return data.techArticleResponseList;
};

export const fetchTechArticlesByCompany = async (companyId, sortType = 'RECENT', page = '', size = 10) => {
    const response = await fetch(`${API_BASE}/tech-articles/companies/${companyId}?sort=${sortType}&page=${page}&size=${size}`);
    if (!response.ok) {
        throw new Error('Failed to fetch tech articles by company');
    }
    const data = await response.json();
    return data.techArticleResponseList;
};