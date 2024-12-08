const API_BASE = process.env.REACT_APP_API_BASE;

export const fetchTechArticles = async (page, size, sort = 'RECENT') => {
    const response = await fetch(`${API_BASE}/tech-articles?page=${page}&size=${size}&sort=${sort}`);
    if (!response.ok) {
        throw new Error('Failed to fetch tech articles');
    }
    const data = await response.json();
    return data.techArticleResponseList;
};

export const fetchTechArticleById = async (id) => {
    const response = await fetch(`${API_BASE}/tech-articles/${id}`);
    if (!response.ok) {
        throw new Error('Failed to fetch tech articles');
    }
    return await response.json();
}

export const fetchRelatedTechArticlesByCompanyId = async (articleId, companyId) => {
    const response = await fetch(`${API_BASE}/tech-articles/${articleId}/companies/${companyId}/related`)
    if (!response.ok) {
        throw new Error('Failed to fetch tech articles');
    }
    const data = await response.json();
    return data.relatedTechArticles;
}

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