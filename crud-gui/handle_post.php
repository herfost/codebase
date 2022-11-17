<?php
require './db/Json.php';
require './configuration/config.php';

if (isset($_POST['submit'])) {
    unset($_POST['submit']);
    $path = $db_path;
    $persistence = new Json($path);
    $is_valid = $persistence->create($_POST);

    if (!$is_valid) {
        echo file_get_contents($generic_error_page);
    }
}