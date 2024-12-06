import React, { forwardRef } from 'react';
import '../styles/articleCard.css';

const ArticleCard = forwardRef((props, ref) => {
    const {
        title,
        author,
        views,
        logoSrc,
        date,
        oneLineSummary,
        threeLineSummary,
        coreSummary,
        link,
        thumbnailSrc
    } = props;

    return (
        <div className="article-card" ref={ref}>
            <div className="thumbnail-img-box">
                <img className="thumbnail-img" src={thumbnailSrc} alt={`${author} thumbnail image`}/>
            </div>
            <div className="article-text-meta-box">
                <div className="article-text-box">
                    <p className="article-title-text">{title}</p>
                    <p className="article-introduction-title">{oneLineSummary}</p>
                </div>
                <div className="article-meta-box">
                    <p className="article-published-date">{date}</p>
                    <img className="article-meta-line" src="/assets/icon/line.svg" alt="line-svg"/>
                    <div className="article-author-view-box">
                        <div className="article-author-box">
                            <img className="article-author-logo" src={logoSrc} alt={`${logoSrc} logo`}/>
                            <span className="article-author">{author}</span>
                        </div>
                        <span className="article-view-count">{views.toLocaleString()} 회</span>
                    </div>
                </div>
            </div>
        </div>
    );
});

export default ArticleCard;
