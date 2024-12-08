import React, { useEffect, useState } from 'react';
import { NavLink } from 'react-router-dom';

import '../styles/recommend.css';
import {fetchRecommendTechArticles} from "../services/TechArticleService";

const Recommend = () => {

    const [articles, setArticles] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    const companyStyles = {
        Toss: { backgroundColor: '#0050FF', color: '#FFFFFF' },
        Woowahan: { backgroundColor: '#FFFFFF', color: '#000000' },
        Kakao: { backgroundColor: '#FEE500', color: '#FFFFFF' },
        Naver: { backgroundColor: '#00C83A', color: '#FFFFFF' },
        default: { backgroundColor: '#CCCCCC', color: '#000000' },
    };

    useEffect(() => {
        const getArticles = async () => {
            try {
                const data = await fetchRecommendTechArticles();
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
        <div className="recommend-box">
            <p className="recommend-header-text">추천 글</p>
            <div className="recommend-article-card-list">
                {articles.map((article) => {
                    const style =
                        companyStyles[article.companyName] ||
                        companyStyles.default;
                    return (
                        <NavLink
                            to={`/articles/${article.id}`}
                            key={article.id}
                            className="recommend-article-card"
                            style={{
                                '--bg-image': `url(${article.imageUrl})`,
                            }}
                        >
                            <div className="recommend-article-title-box">
                                <div
                                    className="company-badge"
                                    style={{
                                        backgroundColor: style.backgroundColor,
                                        color: style.color,
                                    }}
                                >
                                    <span>{article.companyName}</span>
                                </div>
                                <p className="card-title">{article.title}</p>
                            </div>
                        </NavLink>
                    );
                })}
            </div>
        </div>
    );
};

export default Recommend;