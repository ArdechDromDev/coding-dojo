<?php
declare (strict_types=1);
define('LHS', 1);
define('OPERATOR', 2);
define('RHS', 3);

require 'vendor/autoload.php';

function expression(string $expression): int
{
    $matches = [];
    $patterns = [
        "/(.*)([+-])(.*)/",
        "/(.*)([*])(.*)/",
    ];

    $operators = [
        '-' => function ($lhs, $rhs) {
            return $lhs - $rhs;
        },
        '+' => function ($lhs, $rhs) {
            return $lhs + $rhs;
        },
        '*' => function ($lhs, $rhs) {
            return $lhs * $rhs;
        },
    ];

    foreach ($patterns as $pattern) {
        if (preg_match($pattern, $expression, $matches)) {
            $lhs = $matches[LHS];
            $operator = $matches[OPERATOR];
            $rhs = $matches[RHS];

            return $operators[$operator](expression($lhs), expression($rhs));
        }
    }

    return (int)$expression;
}

