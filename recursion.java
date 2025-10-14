void main() {

    IO.println("Playing with recursion");
    int x=recursiveAdd(0,10);
    IO.println(x);

}

// simple recursive loop to reach a target number
int recursiveAdd(int x, int target){
    int xP = x+1;
    if(x==target-1){
        return xP;
    }
    else{
        return recursiveAdd(xP,target);
    }
}
