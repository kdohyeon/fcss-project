import React, {useState} from 'react';
import axios from "axios";

function Dashboard() {
    const [page, setPage] = useState('1');

    const getMovies = async (e) => {
        e.preventDefault();
        console.log(localStorage.getItem('token'))
        const response = await axios.post(`http://localhost:8080/api/v1/movie/search?page=${page}`, {},{
            headers: {
                Authorization: `Bearer ${localStorage.getItem('token')}`,
            }
        }).then(response => {
            console.log(response)
        }).catch(error => {
            console.log(error)
        });

        console.log('movie=', response);
    };

    const handlePage = (e) => {
        setPage(e.target.value)
    };

    return (
        <div className="container d-flex justify-content-center align-items-center vh-100">
            <div className="card shadow-sm p-4" style={{width: '100%', maxWidth: '400px'}}>
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
            </div>
        </div>
    );
}

export default Dashboard;