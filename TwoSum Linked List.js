/**
 * @param {number[]} nums
 * @param {number} target
 * @return {number[]}
 */

// with arrays

var twoSum = function(nums, target) {

for (let i = 0; i < nums.length; i++){

    let firstNum = nums[i];

    for (let j = 0; j < nums.length; j++) {

        if (i != j){
            let secondNum = nums[j];
            let sum = firstNum + secondNum;

            if (sum === target){

            const answer = [i, j]
            return (answer);
            
        }
        
    }

    }

}


};
