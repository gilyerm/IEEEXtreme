#include <iostream>
#include <string.h>
#include <list>
#include <limits.h>
using namespace std;

// DFS with manual Graph impl. (see below)
// https://www.geeksforgeeks.org/detect-cycle-undirected-graph/
class Graph {
    int V;             // No. of vertices
    list<int> *adj;    // Pointer to an array containing adjacency lists
    bool isCyclicUtil(int v, bool visited[], int parent);
public:
    Graph(int V);                 // Constructor
    void addEdge(int v, int w);   // to add an edge to graph
    bool isCyclic();        // returns true if there is a cycle
};

Graph::Graph(int V) {
    this->V = V;
    adj = new list<int>[V];
}
void Graph::addEdge(int v, int w) {
    adj[v].push_back(w); // Add w to v’s list.
    adj[w].push_back(v); // Add v to w’s list.
}
bool Graph::isCyclicUtil(int v, bool visited[], int parent) {
    visited[v] = true;
    list<int>::iterator i;
    for (i = adj[v].begin(); i != adj[v].end(); ++i) {
        if (!visited[*i]) {
           if (isCyclicUtil(*i, visited, v))
              return true;
        }
        else if (*i != parent)
           return true;
    }
    return false;
}
bool Graph::isCyclic() {
    bool *visited = new bool[V];
    for (int i = 0; i < V; i++)
        visited[i] = false;
    for (int u = 0; u < V; u++)
        if (!visited[u]) // Don't recur if already visited
          if (isCyclicUtil(u, visited, -1))
             return true;
    return false;
}

int main()
{
    int tests;  cin >> tests;
    for (int t=0; t<tests; t++) {
        int nodes,edges;
        cin >> nodes >> edges;

        Graph graph(nodes);
        int graphMat[nodes][nodes] = {0}, found = 0;
        memset(graphMat, 0, sizeof(graphMat));

        for (int e=0; e<edges; e++) {
            int i,j;
            cin >> i >> j;
            if (!found) {
                if (graphMat[i][j]) {
                    found = true;
                    continue;
                }
                graph.addEdge(i,j);
                graphMat[i][j]++;
            }
        }

        if (found)
            cout << found << endl;
        else
            cout << (graph.isCyclic() ? 1 : 0) << endl;
    }
}