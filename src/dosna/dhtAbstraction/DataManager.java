package dosna.dhtAbstraction;

import java.util.List;
import java.io.IOException;
import java.util.NoSuchElementException;
import kademlia.dht.GetParameter;
import kademlia.dht.StorageEntry;

/**
 * An abstraction that handles routing data on the network and storing data.
 * This class is an abstraction of the DHT and routing protocol for our system.
 *
 * @author Joshua Kissoon
 * @since 20140326
 */
public interface DataManager
{

    /**
     * Put data onto the network.
     * This will put the data onto the network based on the routing protocol.
     * It may or may not store data locally.
     *
     * @param content The content to be stored on the network
     *
     * @return How many nodes was this content stored on
     *
     * @throws java.io.IOException
     */
    public int put(final DOSNAContent content) throws IOException;

    /**
     * Stores data locally
     *
     * @param content The content to be stored locally
     *
     * @throws java.io.IOException
     */
    public void putLocally(final DOSNAContent content) throws IOException;

    /**
     * Put the content on the local node and on the network.
     *
     * @param content The content to put
     *
     * @return Integer The number of nodes this content was put on, excluding the local node.
     *
     * @throws java.io.IOException
     */
    public int putLocallyAndUniversally(final DOSNAContent content) throws IOException;

    /**
     * Get data from the network.
     * Will get data either from local or remote network nodes.
     *
     * @param gp
     * @param numReaultsReq
     *
     * @return
     *
     * @throws java.io.IOException
     */
    public List<StorageEntry> get(final GetParameter gp, final int numReaultsReq) throws IOException;

    /**
     * Get 1 entry for a data from the network.
     * Will get data either from local or remote network nodes.
     *
     * @param gp
     *
     * @return A single data entry
     *
     * @throws java.io.IOException
     */
    public StorageEntry get(final GetParameter gp) throws IOException, NoSuchElementException;

    /**
     * Run an update call to update the data stored locally on this computer.
     * This may involve deleting some data and adding some other data.
     */
    public void updateStorage();

    /**
     * Save the state of the DataManager and shutdown
     *
     * @param saveState Whether to save the state of the application or not
     *
     * @throws java.io.IOException
     */
    public void shutdown(final boolean saveState) throws IOException;
}
