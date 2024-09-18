import React, {useState} from 'react';
import axios from "axios";

function Dashboard() {
    const [page, setPage] = useState('0');
    const [movies, setMovies] = useState([]);

    const getMovies = async (e) => {
        e.preventDefault();
        console.log(localStorage.getItem('token'))
        const response = await axios.post(`http://localhost:8080/api/v1/movie/search?page=${page}`, {}, {
            headers: {
                Authorization: `Bearer ${localStorage.getItem('token')}`,
            }
        }).then(response => {
            console.log(response)
            setMovies(response.data.data.movies)
        }).catch(error => {
            console.log(error)
        });

        console.log('movie=', response);
    };

    const handlePage = (e) => {
        setPage(e.target.value)
    };

    const like = (movieName) => {
        const response = axios.post(`http://localhost:8080/api/v1/movie/${movieName}/like`, {}, {
            headers: {
                Authorization: `Bearer ${localStorage.getItem('token')}`,
            }
        }).then(response => {
            console.log(response);
        }).catch(error => {
            console.log(error);
        })
    }

    const unlike = (movieName) => {
        const response = axios.post(`http://localhost:8080/api/v1/movie/${movieName}/unlike`, {}, {
            headers: {
                Authorization: `Bearer ${localStorage.getItem('token')}`,
            }
        }).then(response => {
            console.log(response);
        }).catch(error => {
            console.log(error);
        })
    }

    const download = (movieName) => {
        axios.post(`http://localhost:8080/api/v1/movie/${movieName}/download`, {}, {
            headers: {
                Authorization: `Bearer ${localStorage.getItem('token')}`,
            }
        }).then(response => {
            console.log(response);
        }).catch(error => {
            console.log(error);
        })
    }

    return (
        <div className="card shadow-sm p-4" style={{width: '100%'}}>
            <h3 className="text-center mb-4">대시보드</h3>
            <p>여기는 대시보드입니다.</p>
            <input
                type="text"
                id="page"
                value={page}
                onChange={handlePage}
            />
            <button onClick={getMovies}>
                영화 조회
            </button>
            <div className="container mt-4">
                <h2 className="text-center mb-4">영화 데이터</h2>
                <table className="table table-bordered table-hover">
                    <thead className="thead-dark">
                    <tr>
                        <th>영화 이름</th>
                        <th>장르</th>
                        <th>설명</th>
                        <th>좋아요</th>
                        <th>싫어요</th>
                        <th>다운로드</th>
                    </tr>
                    </thead>
                    <tbody>
                    {movies.map(item => (
                        <tr key={item.movieName}>
                            <td>{item.movieName}</td>
                            <td>{item.genre}</td>
                            <td>{item.overview}</td>
                            <td><button onClick={() => like(item.movieName)}>좋아요</button></td>
                            <td><button onClick={() => unlike(item.movieName)}>싫어요</button></td>
                            <td><button onClick={() => download(item.movieName)}>download</button></td>
                        </tr>
                    ))}
                    </tbody>
                </table>
            </div>
        </div>
    );
}

export default Dashboard;