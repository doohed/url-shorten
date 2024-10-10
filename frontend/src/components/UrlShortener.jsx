import React, { useState } from 'react';

function UrlShortener() {
    const [originalUrl, setOriginalUrl] = useState('');
    const [shortUrl, setShortUrl] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            const response = await fetch('http://localhost:8080/api/urls/shorten', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(originalUrl),
            });

            if (response.ok) {
                const shortUrlResponse = await response.text();
                setShortUrl(`http://localhost:8080/${shortUrlResponse}`);
            } else {
                console.error('Failed to shorten URL');
            }
        } catch (error) {
            console.error('Error:', error);
        }
    };

    return (
        <div>
            <h1>URL Shortener</h1>
            <form onSubmit={handleSubmit}>
                <input
                    type="text"
                    value={originalUrl}
                    onChange={(e) => setOriginalUrl(e.target.value)}
                    placeholder="Enter URL"
                    required
                />
                <button type="submit">Shorten</button>
            </form>
            {shortUrl && (
                <p>
                    Shortened URL: <a href={shortUrl}>{shortUrl}</a>
                </p>
            )}
        </div>
    );
}

export default UrlShortener;

