import React from 'react';
import Recommend from './Recommend';
import ArticleSection from "./ArticleSection";
import RankingSection from "./RankingSection";

import '../styles/globals.css';
import '../styles/style.css';

const Main = () => {
    return (
        <div className="main">
            <Recommend />

            <div className="article-section">
                <ArticleSection />
                <RankingSection />
            </div>
        </div>
    );
};

export default Main;
