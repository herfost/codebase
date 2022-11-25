<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-7">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://cdn.tailwindcss.com"></script>
    <title>Form</title>
</head>
<body>
    <?php
        echo file_get_contents('./navbar.html');
    ?>
    <form action="" method="post">
        <label for="name">Name</label>
        <input type="text" name="name" class="border-2 border-black">

        <label for="cost">Cost</label>
        <input type="text" name="cost" class="border-2 border-black">

        <input type="submit" name="new_product" class="bg-green-200">
    </form>
</body>
</html>

<?php
require '../db/Json.php';
$dbPath = '../db/products.json';

if (isset($_POST['new_product'])) {
    unset($_POST['new_product']);

    $persistence = new Json($dbPath);
    $valid = $persistence->create($_POST);

    if (!$valid) { echo file_get_contents('./error.html'); }
}
?>