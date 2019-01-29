(define factorial(
                   lambda (x) (
                                if (< x 3) 
                                x 
                                (* (factorial(- x 1)) x)
                              )
                 )
)
(factorial 3)