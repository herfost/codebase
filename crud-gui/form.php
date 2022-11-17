<?php
require './components/components.php';
require './configuration/config.php';

$values = ['id', 'name', 'surname', 'age'];
$style = 'flex-collumns padding-10';
$action = 'handle_post.php';
$method = 'post';
$content = '';

foreach ($values as $value) $content .= createFormElement($value, $value, $style, $value, $value, $style, '');
$content .= createFormInput('submit', 'submit', $style, '');

echo createForm($action, $method, '', $content);
?><style><?php include $default_css ?></style>