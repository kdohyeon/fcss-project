import React, { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

function KakaoAuthRedirect() {
    const navigate = useNavigate();

    useEffect(() => {
        // 현재 URL에서 카카오 인증 후 리턴된 authorization code 추출
        const code = new URL(window.location.href).searchParams.get("code");

        // 백엔드에 인증 코드 전송하여 JWT 토큰 받기
        if (code) {
            axios.post('http://localhost:8080/api/v1/auth/callback', { code })
                .then(response => {
                    console.log(response)
                    const token = response.data.data.accessToken;  // 백엔드에서 받은 JWT 토큰
                    localStorage.setItem('token', token);  // 토큰을 localStorage에 저장
                    navigate('/dashboard');  // 로그인 후 대시보드로 리디렉션
                })
                .catch(error => {
                    console.error('카카오 로그인 실패:', error);
                });
        }
    }, [navigate]);

    return (
        <div>
            카카오 로그인 처리 중...
        </div>
    );
}

export default KakaoAuthRedirect;