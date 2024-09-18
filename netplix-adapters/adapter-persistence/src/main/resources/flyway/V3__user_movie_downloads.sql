DROP TABLE IF EXISTS `netplix`.`user_movie_downloads`;
CREATE TABLE `netplix`.`user_movie_downloads`
(
    USER_MOVIE_DOWNLOAD_ID VARCHAR(255) NOT NULL COMMENT 'PK',
    USER_ID                VARCHAR(255) NOT NULL COMMENT '사용자 ID',
    MOVIE_ID               VARCHAR(255) NOT NULL COMMENT '영화 ID',

    CREATED_AT             DATETIME     NOT NULL COMMENT '생성일자',
    CREATED_BY             VARCHAR(50)  NOT NULL COMMENT '생성자',
    MODIFIED_AT            DATETIME     NOT NULL COMMENT '수정일자',
    MODIFIED_BY            VARCHAR(50)  NOT NULL COMMENT '수정자',

    PRIMARY KEY (USER_MOVIE_DOWNLOAD_ID)
);