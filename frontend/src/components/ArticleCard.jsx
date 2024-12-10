import React, { forwardRef } from 'react';
import { NavLink } from 'react-router-dom';

import '../styles/articleCard.css';
import companyLogos from "../common/CompanyLogos";

const ArticleCard = forwardRef((props, ref) => {
    const {
        id,
        title,
        author,
        views,
        date,
        oneLineSummary,
        threeLineSummary,
        coreSummary,
        link,
        thumbnailSrc
    } = props;

    return (
        <NavLink
            to={`/articles/${id}`}
            className="article-card" ref={ref}
        >
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
                            <img className="article-author-logo" src={companyLogos[author]} alt={`${author} logo`}/>
                            <span className="article-author">{author}</span>
                        </div>
                        <span className="article-view-count">{views.toLocaleString()} 회</span>
                    </div>
                </div>
            </div>
        </NavLink>
    );
});

export default ArticleCard;
