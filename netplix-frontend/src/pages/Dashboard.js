import React from 'react';

function Dashboard() {
    return (
        <div className="container d-flex justify-content-center align-items-center vh-100">
            <div className="card shadow-sm p-4" style={{width: '100%', maxWidth: '400px'}}>
                <h3 className="text-center mb-4">대시보드</h3>
                <p>로그인에 성공했습니다! 여기는 대시보드입니다.</p>
            </div>
        </div>
    );
}

export default Dashboard;