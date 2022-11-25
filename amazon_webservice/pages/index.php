<?php
require '../db/Json.php';
require '../config/configuration.php';
$dbEmployeePath = '../db/employees.json';
$dbProductPath = '../db/products.json';
$fieldStyle = 'bg-cyan-500 hover:bg-cyan-600 border-2 border-black p-1';
$lineStyle = 'flex justify-center p-1';
$iStyle = 'bg-white-500 hover:bg-white-600 border-2 border-black p-1';
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://cdn.tailwindcss.com"></script>
    <title>Index</title>
</head>
<body>
    <?php echo file_get_contents('./navbar.html'); ?>
    <div class="container justify-evenly px-4">
        <div class="flex justify-center" id="header">

            <?php
                foreach($employeesFields as $field) {
                    echo sprintf('<div class="%s">$%s</div>', $fieldStyle, $field);
                }
            ?>
        </div>
        <?php
            $persistence = new Json($dbEmployeePath);
            $values = $persistence->getAll();
            foreach($values as $value) {
                echo sprintf('<div class="%s">', $lineStyle);
                foreach($value as $i) {
                    echo sprintf('<div class="%s">%s</div>', $iStyle, $i);
                }
                echo '</div>';
            }
        ?>
        <a href="./employee_form.php" class="bg-blue-200">Add</a>
    </div>
    <div class="container justify-evenly px-4">
        <div class="flex justify-center" id="header">
            <?php
                foreach($productsFields as $_field) {
                    echo sprintf('<div class="%s">$%s</div>', $fieldStyle, $_field);
                }
            ?>
        </div>
        <?php
            $_persistence = new Json($dbProductPath);
            $_values = $_persistence->getAll();
           foreach($_values as $_value) {
                echo sprintf('<div class="%s">', $lineStyle);
                foreach($_value as $_i) {
                    echo sprintf('<div class="%s">%s</div>', $iStyle, $_i);
                }
                echo '</div>';
            }
        ?>
        <a href="./product_form.php" class="bg-blue-200">Add</a>
    </div>
</body>
</html>
