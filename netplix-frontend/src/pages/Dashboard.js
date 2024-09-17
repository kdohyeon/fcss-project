import React, {useState} from 'react';
import axios from "axios";

function Dashboard() {
    const [page, setPage] = useState('1');
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
                        <th>ID</th>
                        <th>Title</th>
                        <th>Body</th>
                    </tr>
                    </thead>
                    <tbody>
                    {movies.map(item => (
                        <tr key={item.movieName}>
                            <td>{item.movieName}</td>
                            <td>{item.genre}</td>
                            <td>{item.overview}</td>
                        </tr>
                    ))}
                    </tbody>
                </table>
            </div>
        </div>
    );
}

export default Dashboard;