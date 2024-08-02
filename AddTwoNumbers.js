
let l1 = [2,4,3];
let l2 = [5,6,4];


    let numberOne = 0;
    let numberTwo = 0;
    
    for (let i = 0; i < l1.length; i++) {
        if (i === 0){
            numberOne = (l1[i]);
        } else {
            numberOne += ((Math.pow(10, i)) * (l1[i]));
        }
    }

    for (let i = 0; i < l2.length; i++) {
        if (i === 0){
            numberTwo = (l2[i]);
        } else {
            numberTwo += ((Math.pow(10, i)) * (l2[i]));
        }
    }

    console.log(numberOne);
    console.log(numberTwo);
    console.log(numberOne + numberTwo);
   
    

