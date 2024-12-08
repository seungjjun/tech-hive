import React, { useEffect, useState } from 'react';
import { NavLink } from 'react-router-dom';

import ArticleCard from './ArticleCard';
import { fetchTechArticles } from '../services/TechArticleService';
import '../styles/articleSection.css';

const ArticleSection = () => {
    const [articles, setArticles] = useState([]);
    const [loading, setLoading] = useState(true); // Optional: For loading state
    const [error, setError] = useState(null);     // Optional: For error handling

    const [page, setPage] = useState(1);
    const [limit] = useState(6);

    useEffect(() => {
        const getArticles = async () => {
            try {
                const data = await fetchTechArticles(page, limit);
                setArticles(data);
            } catch (error) {
                console.error(error);
                setError(error.message);
            } finally {
                setLoading(false);
            }
        };
        getArticles();
    }, []);

    if (loading) {
        return <div>Loading articles...</div>;
    }

    if (error) {
        return <div>Error: {error}</div>;
    }

    return (
        <div className="article-box">
            <div className="article-header">
                <p className="article-box-title">최신 아티클 리스트</p>
                <NavLink to="/articles" className={({isActive}) => `article-all-view-button ${isActive ? 'active' : ''}`}>전체 보기</NavLink>
            </div>
            <div className="article-list">
            {articles.map((article) => (
                    <ArticleCard
                        key={article.id}
                        id={article.id}
                        title={article.title}
                        author={article.companyName}
                        views={article.viewCount}
                        logoSrc={`/assets/img/${article.companyName.toLowerCase()}-logo.png`}
                        date={new Date(article.publishedDate).toLocaleDateString()}
                        oneLineSummary={article.oneLineSummary}
                        threeLineSummary={JSON.parse(article.threeLineSummary)}
                        coreSummary={JSON.parse(article.coreSummary)}
                        link={article.link}
                        thumbnailSrc={article.imageUrl}
                    />
                ))}
            </div>
        </div>
    );
};

export default ArticleSection;
