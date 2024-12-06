import React, { useEffect, useState } from 'react';
import { fetchPopularTechArticles } from '../services/TechArticleService';
import '../styles/rankingSection.css';

const RankingSection = () => {
    const [popularArticles, setPopularArticles] = useState([]);

    useEffect(() => {
        const getPopularArticles = async () => {
            try {
                const data = await fetchPopularTechArticles();
                setPopularArticles(data);
            } catch (error) {
                console.error(error);
            }
        };
        getPopularArticles();
    }, []);

    return (
        <div className="ranking-box">
            <p className="ranking-box-title">조회수 랭킹</p>
            <div className="top-rank-article">
                {popularArticles.length > 0 ? (
                    <div className="top-rank-article-box">
                        <div className="top-rank-article-img-box">
                                <img src="/assets/icon/crown-icon.svg" alt="crown-icon" className="crown-icon"/>
                                <img className="top-rank-article-img" src={popularArticles[0].imageUrl}
                                     alt="article-image"/>
                        </div>
                        <div className="top-rank-article-text-box">
                            <div>
                                <p className="top-rank-article-title">{popularArticles[0].title}</p>
                            </div>
                                <div className="top-rank-article-meta">
                                <p className="rank-article-company-name">{popularArticles[0].companyName}</p>
                                <p className="rank-article-view-count">{popularArticles[0].viewCount.toLocaleString()} 회</p>
                            </div>
                        </div>
                    </div>
                ) : (
                    <p>인기 글이 존재하지 않습니다.</p>
                )}
            </div>
            {/*<hr className="ranking-article-line"></hr>*/}
            <div className="rank-articles">
                {popularArticles.length > 1 ? (
                    popularArticles.slice(1).map((article, index) => (
                        <div className="rank-article-box">
                            <span
                                className={`rank-article-rank-number ${
                                    index + 2 === 4 || index + 2 === 5
                                        ? 'rank-article-rank-number-gray'
                                        : ''
                                }`}
                            >
                                {index + 2}
                            </span>
                            <div className="rank-article-thumbnail-img-box">
                                <img className="rank-article-thumbnail-img" src={article.imageUrl}
                                     alt={`${article.title} img`}/>
                            </div>
                            <div className="rank-article-meta-box">
                                <div className="ranking-article-title-box">
                                    <p key={article.id} className="ranking-article-title-text">
                                        {article.title}
                                    </p>
                                </div>
                                <div className="rank-article-meta">
                                    <span className="rank-article-company-name">{article.companyName}</span>
                                    <span
                                        className="rank-article-view-count">{article.viewCount.toLocaleString()} 회</span>
                                </div>
                            </div>
                        </div>
                    ))
                ) : (
                    <p>인기 글이 존재하지 않습니다.</p>
                )}
            </div>
        </div>
    );
};

export default RankingSection;
