<?php
// Example middleware that could log or transform the URLs
if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $url = file_get_contents('php://input');
    // Do something with the URL like logging or transforming
    echo json_encode(['url' => $url]);
}
?>
