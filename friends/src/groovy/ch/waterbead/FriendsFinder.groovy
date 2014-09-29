
import org.neo4j.graphdb.GraphDatabaseService
import org.neo4j.graphdb.Path
import org.neo4j.graphdb.Node
import org.neo4j.graphdb.index.Index
import org.neo4j.graphdb.traversal.Evaluator
import org.neo4j.graphdb.traversal.Evaluation
import org.neo4j.graphdb.traversal.Evaluators
import org.neo4j.graphdb.traversal.TraversalDescription
import org.neo4j.graphdb.traversal.Uniqueness
import ch.waterbead.neo4jloader.Neo4JRegistry
import org.neo4j.graphdb.Direction
import org.neo4j.kernel.Traversal
import static org.neo4j.helpers.collection.IteratorUtil.*

class FriendsFinder
{
    private static final TraversalDescription traversalDescription = 
         Traversal.description()
            .depthFirst()
            .uniqueness
            .relationships( Neo4JRegistry.RelationPersonne.FRIEND_OF, Direction.OUTGOING )
            .evaluator( new Evaluator()
            {
                @Override
                public Evaluation evaluate( Path path )
                {
                    if ( path.length() == 5 )
                    {
                        return Evaluation.INCLUDE_AND_PRUNE;
                    }
                    return Evaluation.INCLUDE_AND_CONTINUE;
                }
            } );
        
    private GraphDatabaseService db
        public FriendsFinder(GraphDatabaseService db) {
            this.db = db
        }

    public int countFriends( long id )
    {
        long currentTime = System.currentTimeMillis();
        Node node = null;
        db.findNodesByLabelAndProperty(Neo4JRegistry.LABEL_PERSONNE,Neo4JRegistry.PROPERTY_PERSONNE_ID,id).each {
            nodeFound ->
            node = nodeFound;
        };
        int value = count(traversalDescription.traverse(node).nodes());
        println "Timed passed " : System.currentTimeMillis()-currentTime;
        return value;
    }
}