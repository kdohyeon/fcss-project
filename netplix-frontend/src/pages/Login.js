import React, {useEffect, useState} from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import 'bootstrap/dist/css/bootstrap.min.css';

function Login({ setIsLoggedIn }) {
    const [username, setUsername] = useState('danny.kim@kakaobank.com');
    const [password, setPassword] = useState('');
    const KAKAO_AUTH_URL = `http://localhost:8080/oauth2/authorization/kakao`;

    const navigate = useNavigate();  // 페이지 이동을 위한 useNavigate 훅 사용

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            // /login API에 POST 요청
            const response = await axios.post('http://localhost:8080/api/v1/auth/login', {
                email: username,
                password
            });

            if (!response.data.success) {
                alert('로그인 실패. ' + response.data.code);
            } else {
                // 응답이 성공하면 로그인 처리 (토큰 저장 등)
                console.log('Login successful:', response.data);
                // 예: localStorage에 토큰 저장
                localStorage.setItem('token', response.data.token);
                setIsLoggedIn(true)
                // 이후 페이지 이동 또는 로그인 처리 로직 추가
                // 예: 대시보드로 이동
                navigate('/dashboard');
            }
        } catch (error) {
            // 오류 처리
            console.error('Login failed:', error);
            alert('로그인 실패: 사용자 정보가 일치하지 않습니다.');
        }
    };

    const handleKakaoLogin = () => {
        window.location.href = KAKAO_AUTH_URL;  // 카카오 로그인 페이지로 리디렉션
    };

    return (
        <div className="container d-flex justify-content-center align-items-center vh-100">
            <div className="card shadow-sm p-4" style={{width: '100%', maxWidth: '400px'}}>
                <h3 className="text-center mb-4">로그인</h3>
                <form onSubmit={handleSubmit}>
                    <div className="mb-3">
                        <label htmlFor="username" className="form-label">Username</label>
                        <input
                            type="text"
                            className="form-control"
                            id="username"
                            value={username}
                            onChange={(e) => setUsername(e.target.value)}
                        />
                    </div>
                    <div className="mb-3">
                        <label htmlFor="password" className="form-label">Password</label>
                        <input
                            type="password"
                            className="form-control"
                            id="password"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                        />
                    </div>
                    <button type="submit" className="btn btn-primary w-100">
                        로그인
                    </button>
                </form>
                <button onClick={handleKakaoLogin} className="btn btn-warning w-100 mt-3">
                    카카오로 로그인
                </button>
            </div>
        </div>
    );
}

export default Login;