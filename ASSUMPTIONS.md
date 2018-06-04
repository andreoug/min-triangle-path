#####Assumptions - Notes

1. While I was reading about the [higher order functions](https://en.wikipedia.org/wiki/Higher-order_function) 
note in the exercise pdf file, I dialectically supposed that I had to implement the solution of a binary tree and then extend it to higher space dimensions. 
When I read again about the higher order functions I left behind this extension. But at least the extension works with the only assumption that the root value should be the same for all the imported file

2. Another approach to implement the exercise is to use graph theory and the [dijkstra's algorithm](https://en.wikipedia.org/wiki/Dijkstra's_algorithm). 
The current minimum triangle path can be transformed to the shortest path between nodes I and II in a graph, where each edge (or neighbor's distance) 
is the inversed and normalized input values. But I didn't follow this approach. 

3. The implemented approach is based on the [BFS search](https://en.wikipedia.org/wiki/Breadth-first_search) using the required higher order functions of java8.
    *  I used two exhaustive searches. One for the minimal value and an extra one for the multiple minimal paths.
    *  If we assume that we need top one and only one solution, then we need only one functional search. 
    *  This implementation is being tested in AllTest's test cases for both latter assumptions).
   
    
4. The input data are following the guidelines definition
    * they are integers.
    * the triangle is always equilateral. 
    * there might have more than one solutions and this should be covered in this implementation. 
    


